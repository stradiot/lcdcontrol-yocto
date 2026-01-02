# LCD Control Yocto Project

Yocto configuration for the [lcdcontrol project](https://github.com/cu-ecen-aeld/final-project-stradiot/wiki).

This repository contains the Yocto Project configuration and metadata required to build a custom embedded Linux distribution for the **LCD Control** project. It targets the Raspberry Pi 4 (Aarch64) and provides a complete system image including a custom kernel driver and userspace application.

## 📂 Project Structure

The project is organized into discrete layers and configuration directories to separate concerns between hardware support, application logic, and product configuration.

```text
.
├── meta-lcdcontrol/       # Application Layer (Driver & User App)
├── meta-rpi-config/       # Product Configuration Layer (Images & Distro)
├── yocto-conf/            # Build Configuration (local.conf, bblayers.conf)
├── setup-env.sh           # Environment setup script
├── poky/                  # [Submodule] Build System
├── meta-raspberrypi/      # [Submodule] BSP Layer
└── meta-openembedded/     # [Submodule] OE Collection
```

---

## 🏗️ Layers & Components

### 1. `meta-lcdcontrol`
**Role:** Application & Hardware Support Logic
This layer contains the core intellectual property of the project. It is designed to be portable and contains the recipes for the custom software stack.

* **`recipes-kernel/lcdcontrol-driver/`**:
    * Builds the `lcdcontrol.ko` out-of-tree kernel module.
    * Handles module signing and installation into `/lib/modules/<kernel-version>/extra/`.
* **`recipes-apps/lcdcontrol-user/`**:
    * Builds the userspace CLI tool (`lcdtool`).
    * Installs the binary to `/usr/bin/` for easy access.

### 2. `meta-rpi-config`
**Role:** Product Configuration & Image Definitions
This layer defines "what" gets built. It sits on top of the BSP and Application layers to assemble the final firmware images.

* **`recipes-core/images/lcdcontrol-image-dev.bb`**:
    * **Purpose:** Development and debugging.
    * **Features:** Includes kernel source packages, headers, debugging tools, build chain and full command line feature set.
    * **Users:** Default `root` user with empty password (debug-tweaks enabled).
* **`recipes-core/images/lcdcontrol-image-prod.bb`**:
    * **Purpose:** Production deployment.
    * **Features:** Minimal footprint. Removes debug and build tools, kernel source, and unnecessary services.
    * **Security:** `debug-tweaks` disabled, requiring secure authentication via SSH keys (preferred), but optionally password can be configured.


### 3. `yocto-conf`
**Role:** Build Environment Configuration
Contains the specific configuration files that define the build environment. Separating this from the standard `build/conf` directory allows for reproducible builds and cleaner SCM management.

* **`local.conf`**:
    * **Machine:** `raspberrypi4-64`
    * **Distro:** `poky` (or custom distro definition)
    * **Package Management:** `ipk`
    * **Variables:** Sets `DL_DIR` and `SSTATE_DIR` to cache shared resources.

---

## 💡 Design Decisions

### Layer Separation Strategy
We adhere to the Yocto "Layer" philosophy to ensure maintainability:
* **BSP Layer (`meta-raspberrypi`)**: unmodified upstream layer. This allows us to update board support easily without breaking our custom code.
* **Logic Layer (`meta-lcdcontrol`)**: Encapsulates the specific driver and app. This layer can be reused in other projects or on other boards with minimal changes.
* **Config Layer (`meta-rpi-config`)**: Defines the specific "product". If we wanted to build a "headless" version or a "GUI" version, we would add new images here without touching the driver code.

### Image Variant Strategy (Dev vs. Prod)
Instead of a single image that requires manual stripping for production, we define two distinct image recipes:
* **Dev:** Optimizes for *developer velocity* (easy access, tools on target).
* **Prod:** Optimizes for *security and size*.
This ensures that debugging tools never accidentally leak into the production firmware.

### Configuration Management
The `yocto-conf` directory abstracts the configuration from the ephemeral `build/` directory. The `setup-env.sh` script links or copies these configurations, ensuring that every developer builds against the exact same project settings (machine, download mirrors, disk space monitoring).

---

## 🚀 Getting Started

### 1. Prerequisites
Ensure your host system has the required Yocto dependencies (Ubuntu/Debian example):
```bash
sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev xterm
```

### 2. Clone the Repository
Clone the project and its submodules recursively:
```bash
git clone --recursive [https://github.com/stradiot/lcdcontrol-yocto.git](https://github.com/stradiot/lcdcontrol-yocto.git)
cd lcdcontrol-yocto
```

### 3. Initialize Environment
Use the provided setup script to configure the build environment:
```bash
source setup-env.sh
```
*This will initialize the `build` directory and configure `conf/local.conf` based on `yocto-conf`.*

### 4. Build the Image
Choose your target image (Dev or Prod):

**Development Build:**
```bash
bitbake lcdcontrol-image-dev
```

**Production Build:**
```bash
bitbake lcdcontrol-image-prod
```

### 5. Flash to SD Card
Once built, flash the wic file to your SD card (replace `/dev/sdX` with your device):
```bash
sudo dd if=tmp/deploy/images/raspberrypi4-64/lcdcontrol-image-dev-raspberrypi4-64.wic.bz2 of=/dev/sdX bs=4M status=progress && sync
```

## 📜 License
MIT / GPL-2.0-only
