
Packet hook & modify from client side demo.  

### start janken server

on server
```
$ python janken_server.py
```

server returns guu/tyoki/paa result randomly.

```
$ curl http://[SERVER_IP]:5000/janken
paa
```

### execute janken from client side (before packet hook&modify)

- execute janken 20 times

```
$ python client/janken_client.py 
You win : your_hand=paa : oponent_hand=guu
Draw : your_hand=paa : oponent_hand=paa
Draw : your_hand=paa : oponent_hand=paa
You win : your_hand=paa : oponent_hand=guu
You lose : your_hand=paa : oponent_hand=tyoki
You lose : your_hand=paa : oponent_hand=tyoki
You lose : your_hand=paa : oponent_hand=tyoki
You lose : your_hand=paa : oponent_hand=tyoki
Draw : your_hand=paa : oponent_hand=paa
You win : your_hand=paa : oponent_hand=guu
Draw : your_hand=paa : oponent_hand=paa
Draw : your_hand=paa : oponent_hand=paa
You lose : your_hand=paa : oponent_hand=tyoki
You lose : your_hand=paa : oponent_hand=tyoki
You lose : your_hand=paa : oponent_hand=tyoki
Draw : your_hand=paa : oponent_hand=paa
Draw : your_hand=paa : oponent_hand=paa
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
Draw : your_hand=paa : oponent_hand=paa
Win:5, Lose:7, Draw:8
```

### execute janken from client side (after packet hook&modify)

- execute packet hook (root required)

```
$ python mitm/scapy_hook.py
```

- execute janken 20 times

```
$ python client/janken_client.py 
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
You win : your_hand=paa : oponent_hand=guu
Win:20, Lose:0, Draw:0
```