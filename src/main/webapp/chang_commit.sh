#!/bin/bash
name=$1
commit=$2
path=$3
sed -i '' 's#<td id=.*>\(.*td\)#<td id="'${name}'">\1#g' /Users/yirendai/Work/webdata/test.sed
sed -i '' 's#\\1.*\\2#\\1'${commit}'\\2#g' /Users/yirendai/Work/webdata/test.sed
sed -i '' -f /Users/yirendai/Work/webdata/test.sed ${path}
