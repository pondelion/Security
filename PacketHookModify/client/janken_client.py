import requests


SERVER_IP = '192.168.111.2'
PORT = 5000

PAA = 'paa'
GUU = 'guu'
TYOKI = 'tyoki'


def main():
    N_REPEAT = 20
    n_win = 0
    n_lose = 0
    n_draw = 0

    for _ in range(N_REPEAT):
        res = requests.get(f'http://{SERVER_IP}:{PORT}/janken')
        oponent_hand = res.content.decode()
        # 自分は毎回パーを出す
        your_hand = PAA
        if (your_hand == PAA and oponent_hand == GUU) or \
            (your_hand == TYOKI and oponent_hand == PAA) or \
            (your_hand == GUU and oponent_hand == TYOKI):
            print(f'You win : your_hand={your_hand} : oponent_hand={oponent_hand}')
            n_win += 1
        elif (oponent_hand == PAA and your_hand == GUU) or \
            (oponent_hand == TYOKI and your_hand == PAA) or \
            (oponent_hand == GUU and your_hand == TYOKI):
            print(f'You lose : your_hand={your_hand} : oponent_hand={oponent_hand}')
            n_lose += 1
        else:
            print(f'Draw : your_hand={your_hand} : oponent_hand={oponent_hand}')
            n_draw += 1
    
    print(f'Win:{n_win}, Lose:{n_lose}, Draw:{n_draw}')


if __name__ == "__main__":
    main()