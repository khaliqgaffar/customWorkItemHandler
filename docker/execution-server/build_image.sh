#!/bin/sh
#
# Builds the Docker container.
#
# author: ddoyle@redhat.com
#
docker build --rm -t jbpm-workshop/kie-server:6.2.0 .
