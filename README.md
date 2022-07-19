**This ReadME.md would include each of the phases and how we completed them and what challenges we faced.**

[Jamie Macaulay Scofield - Scrum Master](https://github.com/JamieScofield)
[Muhammad Mustafa Nawaz - Git Master ](https://github.com/Typist01)
[Mac Murray -](https://github.com/Erratika)
[Alfred Abraham -](https://github.com/AlfredAbra)
[Jeffrey Steven Champion - ](https://github.com/Jchampion42)
[Abdur-Rahmaan Azam - ](https://github.com/abdurshazam)

### TASK
*Build a simulator which will help track the number of people currently training with Sparta Global.*

**Phase One**
- The tracker needs to be able to track time in a consistent way.  
- The program starts by asking how long the simulation will run (e.g number of months/ Neil said ideally program runs for years)  
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

**Phase Two**
- Sparta will now check centres each month.
  - If a centre has fewer than 25 trainees, it will close.
  - The trainees will be randomly moved to another suitable centre.
- The simulation should now offer the choice of summary data at the end of the simulation or a running output updated each month.
- Trainees will now have a course type (Java, C#, Data, DevOps or Business); a trainee will be randomly assigned a course when they are created and this will never change.
- Sparta now has 3 different types of centre - when a new centre can be opened, one of the following will be randomly chosen:
  - Training Hub: can train a maximum of 100 trainees, but 3 (randomly 1-3) can be opened at a time each month.
  - Bootcamp: can train a maximum of 500 trainees, but can remain open for 3 months if there are fewer than 25 trainees in attendance. If a Bootcamp has 3 consecutive months of low attendance, it will close. For the lifetime of the simulation, only 2 Bootcamps can exist at a time.
  - Tech Centre: Can train 200 trainees but only teaches one course per centre. This is chosen randomly when a Tech Centre is opened.
- The simulation should report on the following:
  - number of open centres (breakdown for each type)
  - number of closed centres (breakdown for each type)
  - number of full centres (breakdown for each type)
  - number of trainees currently training (breakdown for each type)
  - number of trainees on the waiting list (breakdown for each type)

**SOLUTION**
We completed the tasks the where issued. 
New trainees get put on a waiting list and the oldest trainees on the waiting list have a higher priority!

