FROM open-liberty

COPY --chown=1001:0  target/mp-miracle.war /config/dropins/
COPY --chown=1001:0  src/main/liberty/config/server.xml /config/
COPY --chown=1001:0  extension /opt/ol/wlp/usr/extension

RUN configure.sh