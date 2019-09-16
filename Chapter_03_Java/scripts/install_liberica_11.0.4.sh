cd /home/pi
wget https://download.bell-sw.com/java/11.0.4/bellsoft-jdk11.0.4-linux-arm32-vfp-hflt.deb
sudo apt-get install ./bellsoft-jdk11.0.4-linux-arm32-vfp-hflt.deb
sudo update-alternatives --config javac
sudo update-alternatives --config java