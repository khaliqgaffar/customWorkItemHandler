#!/bin/sh
echo "Building all Docker images."

echo "Building PostgreSQL base-image."
pushd postgresql
./build_image.sh
popd

echo "Building PostgreSQL BPMSuite image."
pushd postgresql-bpmsuite
./build_image.sh
popd

echo "Building JBoss EAP image."
pushd jboss-eap-6.4
./build_image.sh
popd

echo "Building JBoss BPMSuite image."
pushd bpmsuite
./build_image.sh
popd

echo "Building KIE Execution Server image."
pushd execution-server
./build_image.sh
popd

echo "Finished."
