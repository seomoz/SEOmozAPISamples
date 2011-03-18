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
);

my $t = $seomoz->getLinks( {
    objectURL => 'www.google.com',
    Scope => 'page_to_page',
    Limit => 1,
} );

print Dumper(\$t);

1;