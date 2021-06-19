print("nil: "..type(tostring(nil)))

local protected = {}

function protected.foo1()
    print("foo1");
end

function protected.foo2(...)
    print("foo2");
end

-- Now set the metatable on globals
setmetatable(_G, {
    __index = protected,
    __newindex = function(t, k, v)
        error("全局变量/函数是不被允许的，所有变量/函数必须使用local修饰。[变量：" .. tostring(k) .. "，值：" .. tostring(v) .. "]")
    end
})

setmetatable = nil

return protected