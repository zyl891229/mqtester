#!/bin/bash
set -x
FTP_PATH=$1
PACKAGE_NAME=$2
COMMENT=$3
FTP_FULL_PATH=/Users/yirendai/Work/webdata/ftp/${FTP_PATH}

if [-d ${FTP_FULL_PATH}] 
then
	echo "path exist"
else
	echo "path do not exist"
	mkdir -p ${FTP_FULL_PATH}
fi

if [-d ${FTP_FULL_PATH}/update.html] 
then
	echo "update exist"
else
	echo "update do not exist"
	cp /Users/yirendai/Work/webdata/update_empty.html ${FTP_FULL_PATH}/update.html
fi

sed -i '' '/<!--addstart-->/a\
<tr><td><a href="'./${PACKAGE_NAME}'">'${PACKAGE_NAME}'</a></td><td id="'${PACKAGE_NAME}'">'${COMMENT}'</td></tr>' ${FTP_FULL_PATH}/update.html

