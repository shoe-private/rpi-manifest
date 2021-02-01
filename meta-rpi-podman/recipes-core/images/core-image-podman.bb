SUMMARY = "minimized image"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL} packagegroup-core-ssh-openssh"

GLIBC_GENERATE_LOCALES = "en_US.UTF-8"
IMAGE_LINGUAS ?= "en_US"

LICENSE = "MIT"

inherit core-image

# IMAGE_ROOTFS_SIZE ?= "8192"
# IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"

# https://dev.to/davidjenei/build-your-own-minimal-container-os-with-podman-on-arm-15ia
IMAGE_INSTALL_append = " packagegroup-core-ssh-openssh \
    podman podman-compose \
    crun cgroup-lite rng-tools \
    procps ca-certificates python3-setuptools \
    python3-pyyaml python3-json \
"
#
IMAGE_INSTALL_append += " parted e2fsprogs-resize2fs"
#mdns resolving
IMAGE_INSTALL_append += " libnss-resolve"
#podman complains on missing nsenter, which appears to be missing as an cni rdependency
IMAGE_INSTALL_append += "  bridge-utils util-linux-nsenter"
#install dns-sd service announcements
IMAGE_INSTALL_append += " iputils"
#failed in configure stage.
# IMAGE_INSTALL_append += " htop"
# credentials for root access
IMAGE_INSTALL_append += " sshd-credentials"
IMAGE_INSTALL_append += " sshd-credentials"

export IMAGE_BASENAME = "core-image-podman"
