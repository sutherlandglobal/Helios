#!/usr/local/bin/perl -w

use Archive::Extract;
use File::Basename;
use File::Path  qw( remove_tree);
use File::NCopy;
use strict;

#archivefile is the compressed file containing the platform itself. tomee.zip
my $archiveFile = shift;

die "Invalid archive specified\n" unless $archiveFile;
die "Archive file does not exist\n" unless -e $archiveFile;

#target dir is non-existing dir in a real place, preferably in /opt/, this will be the root install dir
my $targetDir = shift;

die "Invalid target dir specified\n" unless $targetDir;
die "Target dir $targetDir already exists\n" if -e $targetDir;
die "Invalid location for target dir\n" unless -e dirname $targetDir;



#unzip archive file to $targetdir
	#report any errors
#unzip $archiveFile => $targetDir, AutoClose => 1 or die "unzip failed: $UnzipError\n";
my $extractor = Archive::Extract->new(archive =>$archiveFile);
$extractor->extract(to => $targetDir) or die "$@\n";
	
#check if $targetdir contains only a single directory
	#move singledir/* to targetdir
	#rmdir singledir, which should be empty
	
my @extractedFiles = <$targetDir/*>;
my $count = @extractedFiles;
	
print $extractedFiles[0] ." \n";
	
if($count == 1)
{	
	my @archiveFiles = <$extractedFiles[0]/*>;
	my $cp = File::NCopy->new(recursive => 1);
	
	for my $tomeeFile(@archiveFiles)
	{
		$cp->copy("$tomeeFile", $targetDir)	
	}
	
	#remove original files
	remove_tree($extractedFiles[0]);
}
