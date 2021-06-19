local http = {}
local httpForLua = luajava.bindClass("top.littlefogcat.demolist.luaj.base.HttpForLua")

function http:get(url)
    return httpForLua:get(url)
end

return http