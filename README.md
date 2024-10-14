# Lymphocites_vs_Virus_Simulation
A simulation of a body being attacked by a virus
(REQUIRES Java 8 as it includes JavaFX. If not, install JavaFX manually)
 
- In this project, I created a simulation of white blood cells fighting against a virus.
- In a 100x100 grid, it will initially contain one virus and ten lymphocites (White Blood Cells).
- At each turn of the simulation, the virus(es) will move 1 square in any random location (up/down/left/right/diagonals).
- It should not move off the edge of the grid.
- If the virus survives 5 turns, it will reproduce and make a virus spawn in the location of the current virus.
- The White Blood Cells will look for the nearest virus and try to eliminate it by reaching its position.


End Goal:
- If all viruses are eliminated, the lymphocites win.
- If viruses reach a count of a 1000, they win.
