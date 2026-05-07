# N-Body Gravitational Simulator

A JavaFX desktop simulation of the classical N-body gravitational problem. Currently, place 3 bodies on a grid, configure their physical properties, and watch them interact under Newtonian gravity using Velocity Verlet integration.

---

## Features

- Click anywhere on the grid to place a body
- Per-body sidebar controls for velocity, mass, radius, and position
- Dual velocity input — set velocity by speed + angle, or directly by Vx/Vy components, with both staying in sync
- Velocity arrow rendered on each body, updating in real time as sliders change
- Start / Pause, Restart, and Reset controls
- Restart resets bodies to the exact state they were in when Start was first pressed
- Velocity Verlet integration for stable, accurate orbital physics
- Softened gravity to prevent singularities on close approach
- Minimal black and white UI theme

---

## How to Use

1. **Place bodies** — click anywhere on the grid. Each click places one body at that position and opens a tab for it in the sidebar. You need exactly 3 bodies to start.

2. **Configure each body** — select a body's tab in the sidebar to adjust:
    - **Speed / Angle** — sets velocity direction intuitively (0° = right, 90° = up, 180° = left, 270° = down)
    - **SpeedX / SpeedY** — set velocity components directly; speed and angle update automatically
    - **Mass** — heavier bodies exert stronger gravitational pull
    - **Radius** — visual size only, does not affect physics
    - **Center** — reposition the body precisely using sliders

3. **Start** — once all 3 bodies are placed, click Start/Pause to begin the simulation. Click again to pause.

4. **Restart** — resets all bodies to their positions, velocities, and properties at the moment Start was first clicked.

5. **Reset** — clears everything so you can place new bodies from scratch.

---

## Physics

The simulation uses **Velocity Verlet integration**, which is more stable and energy-conserving than simple Euler integration, especially over long simulation times.

Gravity between each pair of bodies is computed as:

```
a = G * M / (d² + ε²)^(3/2) * displacement_unit_vector
```

where `ε` (epsilon) is a softening parameter that prevents the force from blowing up when two bodies get very close.

Constants (configurable in `Constants.java`):

| Constant | Value | Description |
|----------|-------|-------------|
| G | 100 | Gravitational constant |
| epsilon | 5 | Softening parameter |
| timeStep | 0.5 | Physics step size |
| fps | 100 | Simulation frame rate |

---

## Tech Stack

- Java 21
- JavaFX
- Maven

---

## Project Structure

```
src/main/java/com/nihal/nbodyproblem/
├── Animate/        — Application entry point and scene setup
├── Body/           — Body and BodyWrapper (body + velocity arrow)
├── Engine/         — Velocity Verlet physics engine
├── Launcher/       — Main class
├── Timeloop/       — JavaFX animation loop
├── UI/
│   ├── ArrowIcon/  — Velocity arrow (line + triangle arrowhead)
│   ├── SideBar/    — Sidebar, tabs, and per-body slider controls
│   ├── ButtonKey   — Control buttons (Start, Restart, Reset)
│   └── Grid        — Background grid
└── Util/           — Vector math and simulation constants

src/main/resources/
└── Styles.css      — UI theme
```