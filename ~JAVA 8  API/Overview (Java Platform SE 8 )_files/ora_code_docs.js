/*
######################################################
# ORA_CODE_DOCS.JS
# VERSION: 1.02
# ORIGINAL BUILD DATE: 30 March 2015
# COPYRIGHT ORACLE CORP 2015 [UNLESS STATED OTHERWISE]
######################################################
*/
/* Report suite set up */
function s_setAccount(){var sa=["oracledevall","docs","en-us"];sa[0]=(s_checkdev())?"oracledevdocs,oracledevall":"oracledocs,oracleglobal";return sa;}
/* PrePlugins plus site functions */
function s_prePlugins(s){s_oraVer(":docs",":1.02");}function s_checkdev(){var isDev=false;var devSites=new Array();devSites=["-stage","us.oracle.com","-dev","-content","localhost","127.0.0.1","docs-uat"];var al=devSites.length;for(i=0;i<al;i++){if(location.host.indexOf(devSites[i])!=-1){isDev=true;i=al+1;}}return(isDev);}function s_oraVer(_s,_v){_v=_s+_v;oraVersion=(oraVersion.indexOf(_s)==-1)?oraVersion+_v:oraVersion.substr(0,oraVersion.indexOf(_s))+_v;}