# SleepingBarber_Lock
The Sleeping Barber program is implemented using the ReentrantLock and a seprately created queue method.

## Problem
A small barber shop has two doors, an entrance and an exit. Inside is a set of M barbers who spends all their lives serving customers, one at a time. Each barber has chair in which the customer sits when they are getting their hair cut. When there are no customers in the shop waiting for their hair to be cut, a barber sleeps in his chair. Customer arrive at random intervals, with mean mc and standard deviation sdc. If a customer arrives and finds the a barber asleep, he awakens the barber, sits in the barber’s chair and sleeps while his hair is being cut. The time taken to cut a customer's hair has a mean mh and standard deviation sdh. If a customer arrives and all the barbers are busy cutting hair, the customer goes asleep in one of the N waiting chairs. When the barber finishes cutting a customer’s hair, he awakens the customer and holds the exit door open for him. If there are any waiting customers, he awakens one and waits for the customer to sit in the barber's chair, otherwise he goes to sleep.

## Solution:
The sleeping barber problem consists of ‘m’ number of barbers, ‘n’ number of chairs and continuously coming customers where the values of ‘m’ and ‘n’ are user input. As the problem stated, each barber has his own chair where either he will cut the customer’s hair or go to the sleep. On the other hand, the customer will awake a barber if he’s sleeping and sit on the cutting chair or if all barbers are busy then go to the waiting room till their turn will come. One additional condition come here that if all waiting chairs are occupied, then barber will leave the shop.

The solution has two major parts:
1. Reentrant Lock
2. Seprately created a queue as a class

A Reentrant lock is performed before adding or removing any element from the queue. Thus, it will help in acheiving the condition of mutual exclusion in the program.
