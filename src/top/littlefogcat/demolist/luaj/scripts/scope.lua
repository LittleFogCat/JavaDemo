-- main scope
local default = {}

do
    local proxy = {}

    function default:__index(key)
        return proxy[key]
    end

    function default:__newindex(key, value)
        if proxy[key] ~= nil then
            error("Don't change that!")
        end
        proxy[key] = value
    end

    function proxy:foo()
        print("original")
    end

end

return setmetatable({}, default)