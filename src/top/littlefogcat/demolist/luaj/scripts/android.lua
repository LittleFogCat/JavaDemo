Connector = luajava.bindClass("top.littlefogcat.demolist.luaj.LuaJavaConnector")

Connector:setListener(
        function(s)
            print("Lua/收到来自Java的消息：" .. s)
        end
)