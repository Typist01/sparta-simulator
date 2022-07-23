**This ReadME.md would include each of the phases and how we completed them and what challenges we faced.**

[Jamie - Scrum Master](https://github.com/JamieScofield) -
[Mustafa - Git Master ](https://github.com/Typist01) -
[Marc](https://github.com/Erratika) -
[Alfred](https://github.com/AlfredAbra) -
[Jeffrey](https://github.com/Jchampion42) -
[Ray](https://github.com/abdurshazam) -


[PRESENTATION](https://1drv.ms/p/s!AihWGuP9ei1-hSWH1qJt6YPf1mpK?e=WQimcE)

### TASK
*Build a simulator which will help track the number of people currently training with Sparta Global.*

**Technologies Used**
-ENUMS
-NVC pattern
-abstract class
-agile methodology
***solid principles***
-open and close principle
-liskov substitution principle

**Phase One**

- The tracker needs to be able to track time in a consistent way.
- The program starts by asking how long the simulation will run (e.g. number of months/ Neil said ideally program runs
  for years)
- Every month, a random number of trainees are generated, wanting to be trained (50-100)
- Every 2 months, Sparta Global opens training centers; opens instantly and can take trainees every month
- A center can train a max of 100 trainees and takes a random number (0-50) of trainees, not going over capacity
- If a center is full, trainees can be moved to an empty one
- If all are full, trainees go on a waiting list, and are prioritized over newer trainees
- At simulation end, output should show:
    - number of open centers
    - number of full centers
    - number of trainees currently training
    - number of trainees in waiting list
    - 
**Solution**
All the tasks here were completed up to standard.

**Phase Two**

- Sparta will now check centres each month.
    - If a centre has fewer than 25 trainees, it will close.
    - The trainees will be randomly moved to another suitable centre.
- The simulation should now offer the choice of summary data at the end of the simulation or a running output updated
  each month.
- Trainees will now have a course type (Java, C#, Data, DevOps or Business); a trainee will be randomly assigned a
  course when they are created and this will never change.
- Sparta now has 3 different types of centre - when a new centre can be opened, one of the following will be randomly
  chosen:
    - Training Hub: can train a maximum of 100 trainees, but 3 (randomly 1-3) can be opened.
    - Bootcamp: can train a maximum of 500 trainees, but can remain open for 3 months if there are fewer than 25
      trainees in attendance. If a Bootcamp has 3 consecutive months of low attendance, it will close. For the lifetime
      of the simulation, only 2 Bootcamps can exist at a time.
    - Tech Centre: Can train 200 trainees but only teaches one course per centre. This is chosen randomly when a Tech
      Centre is opened.
- The simulation should report on the following:
    - number of open centres (breakdown for each type)
    - number of closed centres (breakdown for each type)
    - number of full centres (breakdown for each type)
    - number of trainees currently training (breakdown for each type)
    - number of trainees on the waiting list (breakdown for each type)
    - 
**Solution**
Phase two has been completed. However, there were a lot of bugs and issues overall. We all banded our minds together to figure 
out what is needed and the best way we could fix them all. OVerall, there was just minor errors in the code and were fixed shortly 
after examination.

**Phase Three**
- If a trainee has been in training for 3 months, they are moved to a bench state.
- Clients will begin to be randomly created after 1 year of the simulation.
- A client will have a requirement when they are created (for example, a need for 27 Java trainees). The requirement can be any value greater than or equal to 15.
- A client will take a random number of trainees from the bench each month (1 - full requirement) until their requirement is met.
- A client will only take one type of trainee (Java, C#, Data, DevOps or Business).
- If a client does not collect enough trainees from the bench within a year, they will leave unhappy.
- If a client does collect enough trainees from the bench within a year, they will leave happy and return the next year with the same requirement.

**SOLUTION**
We completed the tasks that where issued.
New trainees get put on a waiting list and the oldest trainees on the waiting list have a higher priority!
The main issue here is that the trainees were not distributed equally across all centers. The main way we fixed this issue was the use 
of the debugger and the help of out fellow colleagues.

**Final Photos**
![image one](https://cdn.discordapp.com/attachments/999673958536319039/999674047304581170/unknown.png)
![image tew](https://cdn.discordapp.com/attachments/999673958536319039/999674069282721912/unknown.png)
![image three](https://cdn.discordapp.com/attachments/999673958536319039/999674092032643142/unknown.png)
