thomsonPort
===========

Little java aplication to remotely open ports in the Thomson TCW710 router.

I use a vulnerability in the router to be able to change the forwaded ports in the router
remotely without the need of authentification.

This application is based in the one released by Luis Delgado J. 
on http://www.securitybydefault.com/2011/01/modificando-credenciales-en-routers.html

The syntax is:

thomsonPort -h <router_ip> -p idport:localip:InitPort:DestinyPort:state -p ...

In the router you can set until 10 forwading rules. In every execution you must
indicate all the rules (even the uninitialized ones). So the application
only will work if you pass the route ip and ten -p parameters. The router asign a
number to every rule (from 1 to 10). This is the number you must indicate in the
idport value. Also, the rule can vale activated or not. To activate it you should
put the "a" letter as the state value. If not, any other thing will serve.

Maybe, in future updates, I will implement a method to handle the response of the
router in order to know the previous state of the rules so you only need to pass the rules you want to change. But only maybe.