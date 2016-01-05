#!/bin/sh
#
# Builds the Docker container.
#
# author: ddoyle@redhat.com
#
docker build --rm -t jboss-bpmsuite-6/bpmsuite-6.2.0-openshift .
