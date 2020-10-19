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
<br>

influx <br />
Connected to http://localhost:8086 version 1.8.3 <br />
InfluxDB shell version: 1.6.4 <br />
\> use icecast <br>
Using database icecast <br />
\> select * from icecast_listeners <br>
name: icecast_listeners <br />
time                location num_listeners server <br />
----                -------- ------------- ------ <br />
1602847612118000000 /live    15568           http://.....


HLS: ffmpeg -i http:/... -c:a aac -b:a 128k -ac 2 -f hls -hls_time 4 -hls_playlist_type event stream.m3u8cd /var/
