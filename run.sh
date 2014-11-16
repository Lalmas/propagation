#!/bin/sh


#LASSPATH="/home/m1/camaraa/workspace/propagation/classes/"
CLASSPATH="classes"
export CLASSPATH

xterm -e "java serveur.LanceAnnuaire; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 1; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 2; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 3; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 4; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 5; $SHELL" &
sleep 1
xterm -e "java serveur.ServeurImpl 6; $SHELL" &
sleep 1
xterm -e "java serveur.Main $1; $SHELL" &
