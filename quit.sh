#!/bin/sh
ps -A | grep xterm | cut -d " " -f2 | xargs kill -9
