#!/bin/sh
#
# Builds the Docker container.
#
# author: ddoyle@redhat.com
#
docker build --rm -t jbpm-workshop/jboss-bpmsuite:6.2.0 .
