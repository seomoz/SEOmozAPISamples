#!/usr/bin/perl

use strict;
use warnings;
use FindBin qw/$Bin/;
use lib "$Bin/../lib";
use WebService::Mozscape::API;
use Data::Dumper;

my $mozscape = WebService::Mozscape::API->new(
    accessID   => $ENV{MOZSCAPE_ACCESS_ID},
    secretKey  => $ENV{MOZSCAPE_SECRET_KEY},
) or die "Can't init the mozscape instance: " . $WebService::Mozscape::API::errstr;

my $t = $mozscape->getUrlMetrics( {
    objectURL => 'moz.com/blog',
} ) or die $mozscape->errstr;
print Dumper(\$t);

$t = $mozscape->getLinks( {
    objectURL => 'www.google.com',
    Scope => 'page_to_page',
    Sort  => 'page_authority',
    Limit => 1,
} ) or die $mozscape->errstr;
print Dumper(\$t);

1;
