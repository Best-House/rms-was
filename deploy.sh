/usr/local/bin/docker-compose -f ~/rms-was/docker-compose.yml down
/usr/local/bin/docker pull hjsung809/rms:latest
/usr/local/bin/docker-compose -f ~/rms-was/docker-compose.yml up -d