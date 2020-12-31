import os
import sys
from scapy.all import *
from netfilterqueue import NetfilterQueue


TARGET_SERVER_IP = '192.168.111.2'
TARGTE_CLIENT_IP = '192.168.111.10'
TARGET_PORT = 5000


def packet_callback(packet):
    print(packet)
    packet.show()
    try:
        raw = packet[Raw]
        print(f'raw : {raw}')
        print(f'load : {raw.load}')
    except Exception as e:
        print(e)

try:
    sniff(
        filter=f'host {TARGET_SERVER_IP} and port {TARGET_PORT}',
        prn=packet_callback
    )
except KeyboardInterrupt:
    pass
