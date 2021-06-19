-- variables

local inner = {}

function inner.foo()
    print("original foo")
end

function bar()
    print("original bar")
end

setmetatable(_G, {
    __index = inner,
    __newindex = function(t, k, v)
        if not inner[k] then
            rawset(t, k, v)
        end
    end
})

setmetatable = nil