def show_welcome():
    name = input("请输入您的名字: ")  # 获取用户输入
    welcome_message = f"欢迎, {name}!"
    print(welcome_message)  # 打印欢迎信息

def show_sum():
    try:
        n = int(input("请输入一个整数: "))  # 获取用户输入并转换为整数
        total_sum = sum(range(1, n + 1))  # 计算从1加到n的总和
        print(f"总和: {total_sum}")  # 打印总和
    except ValueError:
        print("请输入一个有效的整数")  # 输入无效时显示错误信息

# 调用函数
show_welcome()
show_sum()
