#!/usr/bin/perl

use strict;
use warnings;
use FindBin qw/$Bin/;
use lib "$Bin/../lib";
use WebService::SEOmoz::API;
use Data::Dumper;

my $seomoz = WebService::SEOmoz::API->new(
    accessID   => $ENV{SEOmoz_ID},
    secretKey  => $ENV{SEOmoz_KEY},
) or die "Can't init the seomoz instance: " . $WebService::SEOmoz::API::errstr;

my $t = $seomoz->getUrlMetrics( {
    objectURL => 'www.seomoz.org/blog',
} ) or die $seomoz->errstr;
print Dumper(\$t);

$t = $seomoz->getLinks( {
    objectURL => 'www.google.com',
    Scope => 'page_to_page',
    Sort  => 'page_authority',
    Limit => 1,
} ) or die $seomoz->errstr;
print Dumper(\$t);

1;