import os
import sys
from scapy.all import *
from netfilterqueue import NetfilterQueue


QUEUE_NUMBER = 4
IN_REDIRECT_CMD = f'sudo iptables -I INPUT -p tcp --dport 5000 -j NFQUEUE --queue-num {QUEUE_NUMBER}'
OUT_REDIRECT_CMD = f'sudo iptables -A OUTPUT -p tcp -m tcp --sport 5000 -j NFQUEUE --queue-num {QUEUE_NUMBER}'
RESTORE_IN_CMD = f'sudo iptables -D INPUT -p tcp --dport 5000 -j NFQUEUE --queue-num {QUEUE_NUMBER}'
RESTORE_OUT_CMD = f'sudo iptables -D OUTPUT -p tcp -m tcp --sport 5000 -j NFQUEUE --queue-num {QUEUE_NUMBER}'


def modify_packet(pkt):
    packet = IP(pkt.get_payload())
    try:
        print(f'raw data : {packet[Raw]}')
        docoded_str = bytes(packet[Raw].load).decode()
        # サーバーから受け取ったじゃんけん結果を全てグーに置き換える
        replaced_str = docoded_str.replace('paa', 'guu').replace('tyoki', 'guu')
        packet[Raw].load = replaced_str.encode()
        print(f'modified raw data : {packet[Raw]}')
        packet[IP].len = len(packet)
        del packet[IP].chksum
        del packet[TCP].chksum
    except Exception:
        pass
    pkt.set_payload(bytes(packet))
    pkt.accept()


def packet_hook(pkt):
    packet = IP(pkt.get_payload())
    proto = packet.proto
    # 0x06 = TCP
    print(proto)
    if proto is 0x06:  # TCP protocol
        if packet[TCP].dport == 5000:
            modify_packet(pkt)
        if packet[TCP].sport == 5000:
            modify_packet(pkt)


def main():
    print(IN_REDIRECT_CMD)
    os.system(IN_REDIRECT_CMD)
    print(OUT_REDIRECT_CMD)
    os.system(OUT_REDIRECT_CMD)
    nfqueue = NetfilterQueue()
    nfqueue.bind(QUEUE_NUMBER, packet_hook)

    try:
        nfqueue.run()
    except:
        print('Exiting')
        print(RESTORE_IN_CMD)
        os.system(RESTORE_IN_CMD)
        print(RESTORE_OUT_CMD)
        os.system(RESTORE_OUT_CMD)
        sys.exit(1)


if __name__ == "__main__":
    main()
    os.system(RESTORE_IN_CMD)
    os.system(RESTORE_OUT_CMD)
