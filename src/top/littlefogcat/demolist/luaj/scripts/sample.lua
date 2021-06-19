myListener = function(s)
    print("received Java String: " .. s)
end

_inst_:call(myListener)