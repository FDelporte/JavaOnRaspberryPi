cd /home/pi
wget https://download.bell-sw.com/java/11.0.2/bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.deb
sudo apt-get install ./bellsoft-jdk11.0.2-linux-arm32-vfp-hflt.deb
sudo update-alternatives --config javac
sudo update-alternatives --config java