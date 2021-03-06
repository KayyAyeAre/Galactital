package galactital.io;

import arc.files.*;
import arc.util.*;
import arc.util.io.*;
import galactital.entity.*;
import galactital.game.*;

import java.io.*;
import java.util.zip.*;

public class SaveIO {

    public static void write(Fi to) {
        write(new FastDeflaterOutputStream(to.write(false)));
    }

    public static void read(Fi from) {
        read(new InflaterInputStream(from.read()));
    }

    public static void write(OutputStream os) {
        try (DataOutputStream stream = new DataOutputStream(os)) {
            writePlayers(stream);
            writeSpacecrafts(stream);
        } catch (Exception ignored) {}
    }

    public static void read(InputStream is) {
        try (CounterInputStream counter = new CounterInputStream(is); DataInputStream stream = new DataInputStream(counter)) {
            readPlayers(stream);
            readSpacecrafts(stream);
        } catch (IOException ignored) {}
    }

    public static void writePlayers(DataOutput stream) throws IOException {
        stream.writeInt(Groups.player.size);
        Groups.player.each(p -> p.write(Writes.get(stream)));
    }

    public static void readPlayers(DataInput stream) throws IOException {
        int amount = stream.readInt();
        for (int i = 0; i < amount; i++) {
            new Player().read(Reads.get(stream));
        }
    }

    public static void writeSpacecrafts(DataOutput stream) throws IOException {
        stream.writeInt(Groups.spacecraft.size);
        Groups.spacecraft.each(s -> s.write(Writes.get(stream)));
    }

    public static void readSpacecrafts(DataInput stream) throws IOException {
        int amount = stream.readInt();
        for (int i = 0; i < amount; i++) {
            new Spacecraft().read(Reads.get(stream));
        }
    }
}
