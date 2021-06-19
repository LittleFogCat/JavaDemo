VarargTest = luajava.bindClass("top.littlefogcat.demolist.luaj.base.VarargTest")

VarargTest:varargFun("type_test", { "hello", "world", "!", "" })

local action = function(arg0)
    local n = arg0
    print("n = " .. tostring(n))
    local intent = n.intent
    print("intent = " .. tostring(intent))
    intent:send()
end
local params = {
    title = "微信红包"
}
VarargTest:setTrigger(0, action, params)

function string:startsWith(pattern)
    local index = self:find(pattern)
    return index == 1
end

str = "[2条]非洲野牛: 哦"
print("string = " .. str)
--print(str:startsWith("ello"))
--print(str:startsWith("hell"))
--print(str:find("ello"))
--print(str:find("elloe"))
print(str:find("%[%微%信%红%包%]"))
print(str:find("%[2%条%]%非"))

for i = 1, 10 do
    print(string.sub(str, i, i))
end
str.char()

return function(...)
    for i, v in ipairs({ ... }) do
        print(v .. "/" .. type(v))
    end
end