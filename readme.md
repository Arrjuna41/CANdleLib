# CANdle Subsystem Feature Roadmap

## ✅ Already Implemented (Core Features)

- [x] Solid colors (full strip & segments)
- [x] 9 animation types (Rainbow, Fire, Strobe, Larson, ColorFlow, RgbFade, SingleFade, Twinkle, TwinkleOff)
- [x] Animation configuration (speed, brightness, direction, sparking, cooling, size)
- [x] Custom colors via `Colors.custom(r, g, b)`
- [x] Split strip control (independent segments)
- [x] LED sticking prevention (off-then-set pattern)
- [x] Mock testing support (MockCANdle)
- [x] Comprehensive Javadoc documentation

---

## 🎯 Priority 1: Essential Robot Features

### Alliance Color Auto-Detection
**Purpose:** Automatically show red/blue LEDs based on FMS alliance  
**Implementation:**
- Read `DriverStation.getAlliance()`
- Update LEDs when alliance changes
- Fallback color when not connected to FMS

### Robot State Indicators
**Purpose:** Different LED patterns for each robot mode  
**States to support:**
- Disabled (Orange solid)
- Autonomous (Green strobe)
- Teleop (Blue solid)
- Test (Purple rainbow)
- Emergency Stop (Red fast strobe)

### Game Piece Indicator
**Purpose:** Visual feedback when intake has a game piece  
**Implementation:**
- Boolean sensor input
- Green when detected, Red when empty
- Optional: Different colors for different game pieces

### Battery Level Indicator
**Purpose:** Show battery voltage as LED progress bar  
**Features:**
- Auto-updating based on voltage
- Color-coded: Green (high), Yellow (medium), Red (low)
- Optional audible warning at critical level

### Brightness Control
**Purpose:** Adjust LED brightness without changing colors  
**Implementation:**
- Global brightness scalar (0.0 to 1.0)
- Command to set brightness level
- Store brightness preference

---

## 🔥 Priority 2: Competition Useful

### Level/Progress Indicator
**Purpose:** Generic value-to-LED visual mapper  
**Use cases:**
- Flywheel speed (0-5000 RPM)
- Arm angle (0-90 degrees)
- Pneumatic pressure (0-120 PSI)
- Auto timer countdown

**Features:**
- Configurable min/max range
- Auto-updating via DoubleSupplier
- Color gradient based on percentage

### Flash/Blink Command
**Purpose:** Quick attention-grabbing effect  
**Parameters:**
- Color to flash
- Number of blinks
- Blink rate (Hz)

### Status Light Groups
**Purpose:** Pre-configured patterns for common robot states  
**Groups to implement:**
- Ready to shoot (pulsing green)
- Climbing mode (yellow chase)
- Vision target locked (solid white)
- Communication lost (red blink)
- Low battery (orange pulse)

### Countdown Timer Visual
**Purpose:** Show time remaining in match period  
**Features:**
- 15-second auto warning (color change)
- Final 5 seconds (strobe)
- Configurable for practice vs competition

---

## 🎨 Priority 3: Cool/Polish Features

### Color Wave/Chase
**Purpose:** Sequential color patterns across strip  
**Variations:**
- Single color wave
- Rainbow chase
- Team colors alternating

### Pulse Effect
**Purpose:** Smooth breathing/pulsing animation  
**Parameters:**
- Pulse color
- Pulse speed
- Min/max brightness

### Multi-Zone Effects
**Purpose:** Run different patterns on different segments simultaneously  
**Example:**
- Left half: Battery indicator
- Right half: Shooter speed indicator

### Team Color Themes
**Purpose:** Store and quickly recall team color schemes  
**Features:**
- Save custom RGB values
- Quick-switch between themes
- Import/export color configs

### Victory/Celebration Mode
**Purpose:** Special animation for winning  
**Effects:**
- Rainbow confetti
- Color explosion
- Sparkle effect

---

## 🔧 Priority 4: Advanced/Optional

### Sensor-Driven Animations
**Purpose:** LED effects that respond to real-time sensor data  
**Examples:**
- LEDs brighten as motor speeds up
- Color shifts based on temperature
- Animation speed matches drivetrain velocity

### Pattern Sequences
**Purpose:** Chain multiple effects with precise timing  
**Features:**
- Define sequence of colors/animations
- Set duration for each step
- Loop or one-shot modes

### Zone Temperature Map
**Purpose:** Visual representation of motor temperatures  
**Implementation:**
- Map temperature ranges to colors
- Display across LED strip zones
- Alert on overheating

### Error Code Display
**Purpose:** Flash specific patterns to indicate errors  
**Features:**
- Unique pattern per error type
- Blink count indicates error code
- Override all other patterns when active

### Custom Animation Builder
**Purpose:** Create frame-by-frame custom animations  
**Features:**
- Define LED state for each frame
- Set frame rate
- Save/load custom animations

---

## 🧪 Testing & Tools

### LED Test Mode
**Purpose:** Verify all LEDs are functioning  
**Tests:**
- Individual LED test (one at a time)
- Full strip color test (R, G, B, White)
- Segment test (verify strip zones)

### Color Calibration
**Purpose:** Fine-tune RGB values for specific strip types  
**Features:**
- Test different RGB orders
- Adjust color temperature
- Save calibration per strip type

### Performance Monitor
**Purpose:** Track LED system performance  
**Metrics:**
- Update rate (Hz)
- CAN bus utilization
- Command latency
- Animation smoothness

---

## 📋 Suggested Implementation Order

### Phase 1 (Next Session)
1. Alliance Color Auto-Detection
2. Robot State Indicators
3. Brightness Control

### Phase 2
4. Flash/Blink Command
5. Level Indicator
6. Battery Indicator

### Phase 3
7. Game Piece Indicator
8. Status Light Groups
9. Pulse Effect

### Phase 4
10. Color Wave/Chase
11. Multi-Zone Effects
12. Countdown Timer

### Phase 5+
- Remaining features as needed for competition

---

## 📝 Notes

- All features should maintain the "off-then-set" pattern to prevent LED sticking
- Each feature needs MockCANdle support for testing
- Comprehensive Javadoc required for all public methods
- Test in simulation before deploying to hardware
- Consider power consumption (60 LEDs at full brightness = ~3.6A)

---

## 🎯 Competition Priorities

**Must-Have for Competition:**
- Alliance Color Detection
- Robot State Indicators
- Battery Level Warning

**Nice-to-Have:**
- Game Piece Indicator
- Flash/Blink for alerts
- Victory animation

**Post-Competition:**
- Advanced animations
- Custom builders
- Testing tools