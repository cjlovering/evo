# evo

This is a reinforcement learning environment build in java exploring RL in a
competitive environment. The goal is to create something similar to game of life
where there are two types of agents, where the predators are more powerful but
require more resources. The peasants are weaker, but they could work together.
Currently the way fighting works is that predators have a high chance of inflicting
damage, and peasants have a low chance. Everything can only take one action in a
step. If peasants converge together and attack/trap a predator, there is a higher
chance all their children can survive. Will they scatter or run?

## setup

This project uses *gradle* and currently requires few dependencies, although dl4j
will be added soon. I enable auto-import in my ide (intellij), and everything
works smoothly. 

## design

* currently a session is run, loading and starting the game (very simple)
* its easiest to observe this by running the server and watching it in localhost
    * the speed of the events is turned down to be obervable
    * 0.0.0.0:4567

## experiments

* start training with both of them vs...
    * train each agent type individually first, and then with each other
* try out very weak models but more than random
* try more complicated models
* try different reward systems:
    * what makes sense here? how do you win life?
    * they so far can only evolve by better choosing which action
    * mendelian is harder/ makes slightly less sense/ it would be hard
    to create an entire ecosystem where we can model that.
    * another scheme would be to reward all the actions of the race that survived
    * if training is difficult we could be easier on it, reward any 'good' action
        * mating, winning a fight...
* i have list written done that I think are more intersting than these but I currently forget..

## questions

* do the peasants work together?
* need to find reasonable parameters were its competitive
* do the predators work together? 
* consider adding more pressure, possibly add:
    * peasants die if they don't rest for 2 days out of every 5
    * predators die if they don't east 1 out of 10 days

## plans

* start using dl4j, and implement basic rl models.
* have a server running and give options to users to display different checkpoints
    * try different configurations and numbers of agents
    * select which model to choose
    * having live charts showing relative populations and/or rewards

## tweaks to try

* percentatges for victory for each class for a fight, currently 95% and 5% (pred, prey)
* amount of hunger
* max age

