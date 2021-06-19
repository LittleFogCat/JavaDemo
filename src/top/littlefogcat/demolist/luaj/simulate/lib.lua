local clazz = luajava.newInstance("top.littlefogcat.demolist.luaj.simulate.LuaInterface")

local function delay(millis)
    clazz:delay(millis)
end

local function clickAt(arg)
    clazz:clickAt(arg)
end

local function findViewByText(text)
    return clazz:findViewByText(text)
end

local ids = findViewByText("hello")
print(ids)
print(type(ids))

return {}