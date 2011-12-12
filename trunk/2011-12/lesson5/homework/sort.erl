-module(sort).

%%
%% Exported Functions
%%
-export([test_pmerge_sort/0, pmerge_sort/2]).

%%
%% API Functions
%%

test_pmerge_sort() ->
	% max number of processes
	MaxPids = 10,
	% a list to be sorted
	L = [random:uniform(1000000) || _ <- lists:seq(1, 1000000)],	
	timer:tc(sort, pmerge_sort, [L, MaxPids]).

pmerge_sort(List, MaxProcesses) -> % YOUR ASSIGNMENT.

