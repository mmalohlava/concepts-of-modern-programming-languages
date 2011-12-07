-module(mff).
-compile([export_all]).

flush() -> 
    receive
        Msg -> io:format("You have a message ~p~n", [Msg]),
               flush()
    after 0 -> ok % timeout in ms, 0 = kdyz neni zprava tak skonci
    end.

fib_tr(0, Result, _Next) -> Result;
fib_tr(Iter, Result, Next) when Iter > 0 -> fib_tr(Iter - 1, Result + Next, Result + Next).

fib(N) -> fib_tr(N, 0, 1).
