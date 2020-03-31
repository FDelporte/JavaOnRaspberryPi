cd /home/pi
wget http://download.bell-sw.com/java/14+36/bellsoft-jdk14+36-linux-arm32-vfp-hflt.deb
sudo apt-get install ./bellsoft-jdk14+36-linux-arm32-vfp-hflt.deb
sudo update-alternatives --config javac
sudo update-alternatives --config java
