# DHCP

~~~ shell
# 路由器
# 先给路由器分配ip 192.168.1.254
sys
dhcp enable
dhcp server ip-pool lq
gateway-list 192.168.1.254
newwork 192.168.1.0 mask 255.255.255.0
dns-list 192.168.1.10
expired day 5
# 为PC设置DHCP
~~~



