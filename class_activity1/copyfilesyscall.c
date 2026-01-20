#include <unistd.h>
#include <fcntl.h>

int main() {
    int source_fd, dest_fd; //file descriptors
    char buffer[1024]; //temporary storage
    int bytes_read; //store how many bytes we read
    
    source_fd = open("result.txt", 0);
    if (source_fd == -1) { //-1 for when it fails
        write(1, "Cannot open result.txt\n", 24);
        return 1;
    }
    
    dest_fd = open("copyresult.txt", 577, 0644);
    if (dest_fd == -1) {
        write(1, "Cannot create copyresult.txt\n", 30);
        close(source_fd);
        return 1;
    }
    
    while ((bytes_read = read(source_fd, buffer, 1024)) > 0) {
        write(dest_fd, buffer, bytes_read);
    }
    
    close(source_fd);
    close(dest_fd);
    
    write(1, "File is copied!\n", 16);
    return 0;
}
