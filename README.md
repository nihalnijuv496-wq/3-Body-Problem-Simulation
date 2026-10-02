# N-Body Gravitational Simulator

A JavaFX desktop simulation of the classical N-body gravitational problem. Place N bodies on a grid, configure their physical properties, and watch them interact under Newtonian gravity using Velocity Verlet integration.

---

## Features

- Click anywhere on the grid to place a body
- Per-body sidebar controls for velocity, mass, radius, and position
- Dual velocity input — set velocity by speed + angle, or directly by Vx/Vy components, with both staying in sync
- Velocity arrow rendered on each body, updating in real time as sliders change
- **Preset configurations** — one-click setup for known stable orbits (currently: Lagrange equilateral triangle)
- **Runtime Data tab** — live readout of each body's velocity, position, kinetic energy, potential energy, and total energy; also shows total system energy
- **Orbital trail fading** — each body leaves a fading dot trail as it moves, visualising the path over time
- Start / Pause, Restart, and Reset controls
- Restart resets bodies to the exact state they were in when Start was first pressed
- Velocity Verlet integration for stable, accurate orbital physics
- Softened gravity to prevent singularities on close approach
- Minimal black and white UI theme

---

## How to Use

### Placing bodies manually

1. **Enter N** — Enter desired N value

2. **Place bodies** — click anywhere on the grid. Each click places one body at that position and opens a tab for it in the sidebar. You need exactly N bodies to start.

3. **Configure each body** — select a body's tab in the sidebar to adjust:
    - **Speed / Angle** — sets velocity direction intuitively (0° = right, 90° = up, 180° = left, 270° = down)
    - **SpeedX / SpeedY** — set velocity components directly; speed and angle update automatically
    - **Mass** — heavier bodies exert stronger gravitational pull
    - **Radius** — visual size only, does not affect physics
    - **Center** — reposition the body precisely using sliders

4. **Start** — once all N bodies are placed, click Start/Pause to begin the simulation. Click again to pause.

5. **Restart** — resets all bodies to their positions, velocities, and properties at the moment Start was first clicked.

6. **Reset** — clears everything so you can place new bodies from scratch.

### Using presets

Click a preset button in the sidebar (visible before any bodies are placed) to automatically configure all bodies into a known stable configuration.

**Equilateral Triangle (Lagrange L4/L5)** — places 3 equal-mass bodies at the vertices of an equilateral triangle with velocities tuned for a circular orbit around their common centre of mass. Once loaded:
- Most sliders are locked to preserve the solution's constraints
- **Mass** and **Radius** sliders remain editable
- Changing mass on any body updates all three simultaneously and recalculates the required orbital velocities
- A **Triangle Side** slider is added to each tab, letting you scale the triangle while the velocities adjust automatically

### Monitoring the simulation

Switch to the **Runtime Data** tab (the first tab, available once bodies are placed) to see live values for each body:
- Velocity — magnitude, angle, and Vx/Vy components
- Position — current centre coordinates
- Kinetic Energy, Potential Energy, and Total Energy per body
- **Total System Energy** at the top — useful for checking whether energy is being conserved over time

---

## Physics

The simulation uses **Velocity Verlet integration**, which is more stable and energy-conserving than simple Euler integration, especially over long simulation times.

Gravity between each pair of bodies is computed as:

```
a = G * M / (d² + ε²)^(3/2) * displacement_unit_vector
```

where `ε` (epsilon) is a softening parameter that prevents the force from blowing up when two bodies get very close.

Constants (configurable in `Constants.java`):

| Constant  | Value | Description                          |
|-----------|-------|--------------------------------------|
| G         | 100   | Gravitational constant               |
| epsilon   | 15    | Softening parameter                  |
| timeStep  | 0.1   | Physics step size                    |
| fps       | 100   | Simulation frame rate                |

---

## Tech Stack

- Java 21
- JavaFX
- Maven

---

## Project Structure

```
src/main/java/com/nihal/nbodyproblem/
├── Animate/
│   └── StartAnimation      — JavaFX Application: scene setup, world pane, click-to-add-body handling
├── Body/
│   ├── Body                — Circle body: mass, velocity, color, kinetic/potential energy
│   └── BodyWrapper         — Body + its velocity arrow
├── Engine/
│   └── PhysicsEngine       — Velocity Verlet integrator with adaptive time step
├── Launcher/
│   └── Launcher            — Main class (launches StartAnimation)
├── Presets/
│   ├── Presets             — Enum of available presets + button text
│   └── Lagrange            — Lagrange equilateral triangle preset
├── Timeloop/
│   └── Timeloop            — JavaFX animation loop driving the simulation
├── UI/
│   ├── ArrowIcon/
│   │   ├── Arrow           — Velocity arrow (line + arrowhead)
│   │   └── Triangle        — Arrowhead triangle
│   ├── SideBar/
│   │   ├── SideBar         — ScrollPane sidebar: tab bar, N input box, preset buttons
│   │   ├── Tab             — Tab toggle button (one per body + runtime data)
│   │   ├── DataInputBox    — Per-body sliders (position, velocity, mass)
│   │   ├── RunTimeDataTab  — Live velocity, position and energy readout per body
│   │   └── PresetButton    — Button that loads a preset
│   ├── ButtonKey           — Control buttons (Start, Restart, Reset)
│   ├── CONTROLBUTTON       — Enum for the control button types
│   └── Grid                — Background grid
└── Util/
    ├── Vector              — 2D vector math
    ├── Constants           — Simulation constants, N
    ├── ColorGenerator      — Golden-ratio color generation for any N, lazily generated body/trail colors
    ├── FadeProperty        — Trail fade logic and trail color
    └── PresetUtils         — Preset loading and equilateral-triangle position/velocity helpers

src/main/resources/
└── Styles.css              — UI theme
```