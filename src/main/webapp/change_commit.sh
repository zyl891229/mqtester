#!/bin/bash
set -x
name="$1"
commit="$2"
path="$3"

echo ${name}
echo ${commit}
echo ${path}

sedpath=/Users/yirendai/Work/webdata/test.sed

sed -i '' 's#<td id=.*>\(.*td\)#<td id="'${name}'">\1#g' ${sedpath}
sed -i '' 's#\\1.*\\2#\\1'${commit}'\\2#g' ${sedpath}
sed -i '' -f ${sedpath} ${path}
