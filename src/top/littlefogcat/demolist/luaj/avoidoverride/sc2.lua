---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by littlefogcat.
---


print("sc2")

observable = luajava.newInstance("top.littlefogcat.demolist.luaj.avoidoverride.Observable")


callback = function()
    --print("sc2 callback")
    --foo()
end

--hello()

observable:observe("sc2",callback)