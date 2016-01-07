rf -rf moose-*/target
myfile=$(date +'%Y%m%d_SWTK-ENG-%s.tar.gz')
tar cvf $myfile .
mv $myfile $EOD
