# 
# Docker base image of JBoss BPMSuite 6.1.0
#
#FROM ddoyle/jboss-eap:6.4.0
FROM registry.access.redhat.com/jboss-eap-6/eap64-openshift

MAINTAINER ddoyle <ddoyle@redhat.com>

EXPOSE 9990

# Adding jboss user to sudoers file
USER root
RUN echo "root:redhat" | chpasswd
#RUN yum install sudo
#RUN echo 'jboss  ALL=(ALL:ALL) ALL' >> /etc/sudoers
USER jboss

# Add default JBoss EAP admin user
RUN /opt/eap/bin/add-user.sh admin jboss@01 --silent

# Copy JBoss BPM-Suite
RUN mkdir /tmp/dockerfile_copy
COPY dockerfile_copy/jboss-bpmsuite-6.2.0.GA-deployable-eap6.x.zip /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-deployable-eap6.x.zip
COPY dockerfile_copy/jboss-bpmsuite-6.2.0.GA-supplementary-tools.zip /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-supplementary-tools.zip

#Install BPMSuite 
RUN mkdir /tmp/dockerfile_copy/unzip_tmp && \
	/usr/bin/unzip -qo /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-deployable-eap6.x.zip -d /tmp/dockerfile_copy/unzip_tmp && \
	cp /tmp/dockerfile_copy/unzip_tmp/jboss-eap-6.4/bin/* /opt/eap/bin && \
	cp /tmp/dockerfile_copy/unzip_tmp/jboss-eap-6.4/domain/configuration/domain.xml /opt/eap/domain/configuration/ && \
	cp /tmp/dockerfile_copy/unzip_tmp/jboss-eap-6.4/standalone/configuration/* /opt/eap/standalone/configuration/ && \
	cp -R /tmp/dockerfile_copy/unzip_tmp/jboss-eap-6.4/standalone/deployments/* /opt/eap/standalone/deployments/

RUN /usr/bin/unzip -qo /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-supplementary-tools.zip -d /opt/eap

# Create initial BPMSuite users.
RUN /opt/eap/bin/add-user.sh -a -r ApplicationRealm -u bpmsAdmin -p jboss@01 -ro admin,analyst,developer --silent

# Clean up
USER root
# For one reason or the other, we need to delete this file first for the 'rm -rf' to work ... Got no idea why .....
RUN rm /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-deployable-eap6.x.zip && rm /tmp/dockerfile_copy/jboss-bpmsuite-6.2.0.GA-supplementary-tools.zip && rm -rf /tmp/dockerfile_copy/unzip_tmp
RUN rm -rf /tmp/dockerfile_copy
USER jboss

# Define the entrypoint and cmd.
ENTRYPOINT ["/opt/eap/bin/standalone.sh"]
CMD ["-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
