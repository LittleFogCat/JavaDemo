---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by littlefogcat.
---
function foo()
    print("original")
end

for a,b in pairs(_G) do
    print(a,"\t",b)
end