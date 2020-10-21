# Icecast2GoogleAnalytics
<h2>v1</h2>
https://coherent-receiver.com/google-analytics-and-icecast - for more details <br /> 
https://github.com/ned-kelly/docker-icecast-google-analytics - dockerised version  <br />
https://coherent-receiver.com/multi-channel-receivers-for-radio-station-monitoring - hardware platform for broadcasting monitoring, transcoding or hybrid radio <br />

Please don't hesitate to contact us if you have additional requirements, wishes or ideas 
<br />
<h2>v2</h2>
<h3>Dependencies</h3>
Grafana:
docker run -d -p 3000:3000 grafana/grafana <br />
InfluxDB: docker run -p 8086:8086  -v $PWD/influxdb:/var/lib/influxdb:rw influxdb <br />
<br />
https://blog.maxmind.com/2019/12/18/significant-changes-to-accessing-and-using-geolite2-databases/
<br />
https://db-ip.com/db/download/ip-to-city-lite


<br />
For HLS distribution
HLS: ffmpeg -i http:/... -c:a aac -b:a 128k -ac 2 -f hls -hls_time 4 -hls_playlist_type event stream.m3u8cd /var/
