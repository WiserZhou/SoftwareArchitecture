# 选择官方的 Python 3.12 镜像作为基础镜像
FROM python:3.12

# 安装其他依赖
RUN apt-get update && apt-get install -y \
    && rm -rf /var/lib/apt/lists/*

# 设置工作目录
WORKDIR /test

# 将当前目录的代码复制到容器中
COPY . /test

# 安装 Python 的依赖
# RUN pip install --no-cache-dir -r requirements.txt

# 设置容器启动时的命令
CMD ["python", "test.py"]
