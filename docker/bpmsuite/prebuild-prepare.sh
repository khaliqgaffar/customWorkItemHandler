#!/bin/sh
pushd create-postgresql-module-zip
./build-postgresql-module-zip.sh
popd
cp create-postgresql-module-zip/postgresql-module.zip dockerfile_copy/postgresql-module.zip

